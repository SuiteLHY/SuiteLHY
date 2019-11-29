package github.com.SuiteLHY.thinkingInJava.chapter17.ContainersInDepth;

import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

class CollectionUtil {

    public static <T> Collection<T> shallowCopy(@NotNull Collection<T> src) {
        Collection<T> dest = newInstance(src.getClass());
        if (null != dest) {
            for (T element : src) {
                dest.add(element);
            }
        }
        return dest;
    }

    public static <T> Collection<T> deepCopy(@NotNull Collection<T> src)
            throws IOException, ClassNotFoundException {
        return (Collection<T>) deepCopy((Object) src);
    }

    private static <T, E extends java.io.Serializable> T deepCopy(@NotNull T src)
            throws ClassNotFoundException, IOException {
        Class<?> type = src.getClass();
        if (null != type && Collection.class.isAssignableFrom(type)) {
            //--- 参数对象为集合的情况
            Collection<Object> dest = (Collection<Object>) newInstance(type);
            for (Object element : ((Collection<?>) src)) {
                dest.add(deepCopy(element));
            }
            return (T) dest;
        }
        return (T) serializedCopy((E) src);
    }

    /**
     * 深拷贝
     * @param src
     * @param <T>
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private static <T extends java.io.Serializable> T serializedCopy(@NotNull T src)
            throws ClassNotFoundException, IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(src);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw e;
        }
    }

    private static <T> T shallowCopy(@NotNull T src) {
        return src;
    }

    private static <T> T newInstance(@NotNull Class<T> srcClass) {
        T newInstance = null;
        try {
            newInstance = srcClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return newInstance;
    }

}

/**
 * 【coding】Collection的迭代遍历
 *
 * @author Suite
 */
@Component
public class IteratingOverCollections {

    private static void show(Object arg) {
        if (null != arg) {
            String content = (arg instanceof String) ? (String) arg : arg.toString();
            System.out.println(content);
        }else{
            System.out.println();
        }
    }

    /**
     * Iterator迭代遍历中的正确操作示例
     * @param collection
     * @param <E>
     * @return
     */
    private static <E extends Collection> boolean showIterator(E collection) {
        show("=== Iterator迭代遍历中的正确操作示例： ===");
        show(collection);
        for (Iterator<Object> iterator = collection.iterator(); iterator.hasNext();) {
            Object each;
            each = iterator.next();
            show("-- " + each);
            if (each instanceof String) {
                // 删除迭代器当前定位的元素（正确操作）
                iterator.remove();
                show("-- 成功删除集合中的元素：" + each);
            }
        }
        show(collection);
        show(null);
        return true;
    }

    /**
     * Iterator迭代遍历中的错误操作警示
     * @param collection
     * @param <E>
     * @return
     */
    private static <E extends Collection> boolean showWrongOperationOnIterator(E collection) {
        boolean result = true;
        show("=== Iterator迭代遍历中的错误操作警示： ===");
        show(collection);
        for (Iterator<Object> iterator = collection.iterator(); iterator.hasNext();) {
            Object each;
            try {
                //!ERROR: java.util.ConcurrentModificationException
                each = iterator.next();
            } catch (ConcurrentModificationException e) {
                show("-- 在Iterator迭代遍历中获取集合元素失败！异常信息如下：");
                System.err.println(e);
                result = false;
                break;
            }
            show("-- " + each);
            if (each instanceof String) {
                try {
                    collection.remove(each);
                } catch (ConcurrentModificationException e) {
                    show("-- 在Iterator迭代遍历中删除集合元素失败！异常信息如下：");
                    System.err.println(e);
                    result = false;
                    break;
                }
                show("-- 成功删除集合中的元素：" + each);
            }
        }
        show(collection);
        show(null);
        return result;
    }

    /**
     * forEach迭代遍历中的正确操作示例
     * @param collection
     * @param <E>
     * @return
     */
    private static <E extends Collection> boolean showForEach(E collection) {
        show("=== forEach迭代遍历中的正确操作示例： ===");
        show(collection);
        for (Iterator<Object> iterator = collection.iterator(); iterator.hasNext();) {
            Object each;
            each = iterator.next();
            show("-- " + each);
            if (each instanceof String) {
                // 删除迭代器当前定位的元素（正确操作）
                iterator.remove();
                show("-- 成功删除集合中的元素：" + each);
            }
        }
        show(collection);
        show(null);
        return true;
    }

    /**
     * forEach迭代遍历中的错误操作警示
     * @param collection
     * @param <E>
     * @return
     */
    private static <E extends Collection> boolean showWrongOperationOnForEach(E collection) {
        boolean result = true;
        show("=== forEach迭代遍历中的错误操作警示： ===");
        show(collection);
        try {
            for (Object each : collection) {
                show("-- " + each);
                if (each instanceof String) {
                    try {
                        collection.remove(each);
                    } catch (ConcurrentModificationException e) {
                        show("-- 在forEach迭代遍历中删除集合元素失败！异常信息如下：");
                        System.err.println(e);
                        result = false;
                        break;
                    }
                    show("-- 成功删除集合中的元素：" + each);
                }
            }
        } catch (Exception e) {
            show("-- 在forEach迭代遍历中获取集合元素失败！异常信息如下：");
            e.printStackTrace();
            result = false;
        }
        show(collection);
        show(null);
        return result;
    }


    public void codeAfterThinking() {
        //----- Collection的迭代遍历
        Collection<Object> collection = new ArrayList<>();
        collection.add("1");
        collection.add("2");
        collection.add("3");
        Collection<Object> collection_copy = null;
        try {
            collection_copy = CollectionUtil.deepCopy(collection);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Collection<Object> collection1 = null;
        try {
            collection1 = CollectionUtil.deepCopy(collection);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Collection<Object> collection1_copy = null;
        try {
            collection1_copy = CollectionUtil.deepCopy(collection1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 【方式1】 Iterator迭代遍历
        show("//===== 【方式1】 Iterator迭代遍历： =====//");
        showIterator(collection);
        showWrongOperationOnIterator(collection_copy);
        show("//==========//");
        show(null);

        // 【方式2】 forEach迭代遍历
        show("//===== 【方式2】 forEach迭代遍历： =====//");
        showForEach(collection1);
        showWrongOperationOnForEach(collection1_copy);
        show("//==========//");
        show(null);
    }

}
