package com.mkst.mini.systemplus.resful.utils;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BeanCopyUtils {
    /**
     * 使用缓存提高效率
     */
    private static final ConcurrentHashMap<String, BeanCopier> BEAN_COPIER_MAP = new ConcurrentHashMap<>();

    /**
     * 从源对象 Copy 目标 Class
     *
     * @param source      原对象
     * @param targetClass 目标对象的 Class
     * @param action      对 Copy 结束后的特殊处理逻辑
     * @return 目标对象
     */
    public static <O, T> T coypToClass(O source, Class<T> targetClass, IAction<T> action) {
        T target = null;
        try {
            target = targetClass.getDeclaredConstructor().newInstance();
            baseCopy(source, target);
            if (null != action) {
                action.run(target);
            }
        } catch (Exception e) {
            log.error("【Copy 对象属性】-Copy错误：${}$", ExceptionUtils.getStackTrace(e));
            throw new ServiceException(e.getMessage());
        }
        return target;
    }

    /**
     * 从源对象 Copy 目标对象
     *
     * @param source 原对象
     * @param target 目标对象
     * @param action 对 Copy 结束后的特殊处理逻辑
     * @return 目标对象
     */
    public static <O, T> T coypToObject(O source, T target, IAction<T> action) {
        try {
            baseCopy(source, target);
            if (null != action) {
                action.run(target);
            }
        } catch (Exception e) {
            log.error("【Copy 对象属性】-Copy错误：${}$", ExceptionUtils.getStackTrace(e));
            throw new ServiceException(e.getMessage());
        }
        return target;
    }

    /**
     * 从源对象集合 Copy 目标 Class 集合
     *
     * @param sourceList  原对象集合
     * @param targetClass 目标对象的 Class
     * @param action      对 Copy 结束后的特殊处理逻辑
     * @return 目标对象集合
     */
    public static <O, T> List<T> coypToClassList(List<O> sourceList, Class<T> targetClass, IAction<T> action) {
        LinkedList<T> targetList = new LinkedList<>();
        try {
            if (null != sourceList && !sourceList.isEmpty()) {
                for (O source : sourceList) {
                    T target = targetClass.getDeclaredConstructor().newInstance();
                    baseCopy(source, target);
                    targetList.add(target);
                    if (null != action) {
                        action.run(target);
                    }
                }
            }
        } catch (Exception e) {
            log.error("【Copy 对象属性】-Copy错误：${}$", ExceptionUtils.getStackTrace(e));
            throw new ServiceException(e.getMessage());
        }
        return targetList;
    }

    private static <O, T> T baseCopy(O source, T target) {
        String baseKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier;
        if (!BEAN_COPIER_MAP.containsKey(baseKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            BEAN_COPIER_MAP.put(baseKey, copier);
        } else {
            copier = BEAN_COPIER_MAP.get(baseKey);
        }
        copier.copy(source, target, null);
        return target;
    }

    private static String generateKey(Class<?> classSource, Class<?> classTarget) {
        return new StringBuilder().append(classSource.toString()).append("-").append(classTarget.toString()).toString();
    }

}

@FunctionalInterface
interface IAction<T> {
    void run(T param);
}

/**
 * 测试用
 */
class BeanUtils {

    /**
     * the beanCopierMap
     */
    private static final ConcurrentMap<String, BeanCopier> BEAN_COPIER_MAP = new ConcurrentHashMap<>();

    /**
     * @param source
     * @param target
     * @return T
     * @description 两个类对象之间转换
     */
    public static <T> T convert(Object source, Class<T> target) {
        T ret = null;
        if (source != null) {
            try {
                ret = target.newInstance();
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException("create class[" + target.getName()
                        + "] instance error", e);
            }
            BeanCopier beanCopier = getBeanCopier(source.getClass(), target);
            beanCopier.copy(source, ret, new DeepCopyConverter(target));
        }
        return ret;
    }

    public static class DeepCopyConverter implements Converter {

        /**
         * The Target.
         */
        private Class<?> target;

        /**
         * Instantiates a new Deep copy converter.
         *
         * @param target the target
         */
        public DeepCopyConverter(Class<?> target) {
            this.target = target;
        }

        @Override
        public Object convert(Object value, Class targetClazz, Object methodName) {
            if (value instanceof List) {
                List values = (List) value;
                List retList = new ArrayList<>(values.size());
                for (final Object source : values) {
                    String tempFieldName = methodName.toString().replace("set",
                            "");
                    String fieldName = tempFieldName.substring(0, 1)
                            .toLowerCase() + tempFieldName.substring(1);
                    Class clazz = ClassUtils.getElementType(target, fieldName);
                    retList.add(BeanUtils.convert(source, clazz));
                }
                return retList;
            } else if (value instanceof Map) {
                // TODO：大漠知秋 - 暂时用不到，后续有需要再补充
            } else if (!ClassUtils.isPrimitive(targetClazz)) {
                return BeanUtils.convert(value, targetClazz);
            }
            return value;
        }
    }

    /**
     * @param source
     * @param target
     * @return BeanCopier
     * @description 获取BeanCopier
     */
    public static BeanCopier getBeanCopier(Class<?> source, Class<?> target) {
        String beanCopierKey = generateBeanKey(source, target);
        if (BEAN_COPIER_MAP.containsKey(beanCopierKey)) {
            return BEAN_COPIER_MAP.get(beanCopierKey);
        } else {
            BeanCopier beanCopier = BeanCopier.create(source, target, true);
            BEAN_COPIER_MAP.putIfAbsent(beanCopierKey, beanCopier);
        }
        return BEAN_COPIER_MAP.get(beanCopierKey);
    }

    /**
     * @param source
     * @param target
     * @return String
     * @description 生成两个类的key
     */
    public static String generateBeanKey(Class<?> source, Class<?> target) {
        return source.getName() + "@" + target.getName();
    }
}

class ClassUtils {

    private static final Map<Class<?>, Class<?>> PRIMITIVE_MAP = new HashMap<>(9);

    static {
        PRIMITIVE_MAP.put(String.class, String.class);
        PRIMITIVE_MAP.put(Boolean.class, boolean.class);
        PRIMITIVE_MAP.put(Byte.class, byte.class);
        PRIMITIVE_MAP.put(Character.class, char.class);
        PRIMITIVE_MAP.put(Double.class, double.class);
        PRIMITIVE_MAP.put(Float.class, float.class);
        PRIMITIVE_MAP.put(Integer.class, int.class);
        PRIMITIVE_MAP.put(Long.class, long.class);
        PRIMITIVE_MAP.put(Short.class, short.class);
        PRIMITIVE_MAP.put(Date.class, Date.class);
    }

    /**
     * @param clazz
     * @return boolean
     * @description 判断基本类型
     * @see String
     * @see Boolean
     * @see Character
     * @see Byte
     * @see Short
     * @see Integer
     * @see Long
     * @see Float
     * @see Double
     * @see Boolean
     * @see char
     * @see byte
     * @see short
     * @see int
     * @see long
     * @see float
     * @see double
     */
    public static boolean isPrimitive(Class<?> clazz) {
        if (PRIMITIVE_MAP.containsKey(clazz)) {
            return true;
        }
        return clazz.isPrimitive();
    }

    /**
     * @param tartget
     * @param fieldName
     * @return Class<?>
     * @description 获取方法返回值类型
     */
    public static Class<?> getElementType(Class<?> tartget, String fieldName) {
        Class<?> elementTypeClass = null;
        try {
            Type type = tartget.getDeclaredField(fieldName).getGenericType();
            ParameterizedType t = (ParameterizedType) type;
            String classStr = t.getActualTypeArguments()[0].toString().replace("class ", "");
            elementTypeClass = Thread.currentThread().getContextClassLoader().loadClass(classStr);
        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException e) {
            throw new RuntimeException("get fieldName[" + fieldName + "] error", e);
        }
        return elementTypeClass;
    }

}
