    /**
     * 空校验 null或""都返回true
     * @param obj
     * @returns {boolean}
     */
    function isEmpty(obj) {
        if ((typeof obj === 'string')) {
            return !obj || obj.replace(/\s+/g, "") === ""
        } else {
            return (!obj || JSON.stringify(obj) === "{}" || obj.length === 0);
        }
    }

    /**
     * 非空校验
     * @param obj
     * @returns {boolean}
     */
    function isNotEmpty(obj) {
        return !isEmpty(obj);
    }

    /**
     * 对象拷贝
     * @param obj
     * @returns {any}
     */
    function copy(obj) {
        if (isNotEmpty(obj)) {
            return JSON.parse(JSON.stringify(obj));
        }
    }

    export {
        isEmpty,
        isNotEmpty,
        copy
    }