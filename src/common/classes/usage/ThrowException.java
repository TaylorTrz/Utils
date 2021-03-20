package common.classes.usage;

/**
 * 给定返回错误类型与错误原因
 */

public class ThrowException {

    public String toString() {

        // 验证getClass()与getName()方法
        String s = this.getClass().getName();
        String message = getLocalizedMessage();
        return (message != null) ? (s + ": " + message) : s;
    }

    private String getLocalizedMessage() {
        return "return getLocalizedMessage";
    }
}
