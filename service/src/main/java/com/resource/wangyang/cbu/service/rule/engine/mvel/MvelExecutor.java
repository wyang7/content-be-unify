package com.resource.wangyang.cbu.service.rule.engine.mvel;

import com.google.common.collect.Sets;
import com.resource.wangyang.cbu.service.rule.context.RuleContext;
import com.resource.wangyang.cbu.service.rule.engine.RuleExecutor;
import com.resource.wangyang.cbu.service.rule.engine.function.MvelFunction;
import lombok.extern.slf4j.Slf4j;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.impl.CachingMapVariableResolverFactory;
import org.reflections.Reflections;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wyang7
 */
@Slf4j
public abstract class MvelExecutor implements RuleExecutor {



    private Map<String, Serializable> expressionMap = new ConcurrentHashMap<>();

    /**
     * 排除包含如下语句的表达式
     */
    private static final Set<String> PREVENT_EXPRESSION = Sets.newHashSet("Runtime.getRuntime().exit", "System.exit");

    private static final ParserContext parserContext = new ParserContext();

    /**
     * 加载自定义表达式
     */
    static {
        Reflections reflections = new Reflections("com.resource.wangyang.cbu.*");
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(MvelFunction.class);
        if (!CollectionUtils.isEmpty(typesAnnotatedWith)) {
            typesAnnotatedWith.forEach(aClass -> {
                Method[] methods = aClass.getMethods();
                for (Method method : methods) {
                    MvelFunction mvelFunction = method.getAnnotation(MvelFunction.class);
                    if (mvelFunction != null) {
                        parserContext.addImport(method.getName(), method);
                    }
                }
            });
        }
    }

    /**
     * 表达式执行
     *
     * @param expression 表达式
     * @param params     环境变量
     * @return 表达式执行结果
     */
    @Override
    public Object execute(String expression, Map<String, Object> params) {
        try {
            Serializable serializable = get(expression);
            if (serializable == null) {
                return false;
            }

            CachingMapVariableResolverFactory factory = new CachingMapVariableResolverFactory(params);
            Object result = ((ExecutableStatement) serializable).getValue(null, factory);

            return result;
        } catch (Exception e) {
            log.error("@execute error:", e);
        }
        return false;
    }


    protected Serializable get(String expression) {
        Serializable serializable;
        if (expressionMap.containsKey(expression)) {
            serializable = expressionMap.get(expression);
        } else {
            serializable = compile(expression);
            if (serializable != null) {
                expressionMap.put(expression, serializable);
            }
        }
        return serializable;
    }

    /**
     * 表达式编辑
     *
     * @param expression 表达式
     * @return 序列化对象
     */
    private synchronized Serializable compile(String expression) {
        try {
            if (expression == null) {
                return null;
            }
            for (String expr : PREVENT_EXPRESSION) {
                if (expression.contains(expr)) {
                    return null;
                }
            }
            return MVEL.compileExpression(expression, parserContext);
        } catch (Exception e) {
            log.debug("@compile e:", e);
        }
        return null;
    }
}
