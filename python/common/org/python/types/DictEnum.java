package org.python.types;

/**
 * Enum class for types.dict
 * Last edited: 08/10/2020
 */
public enum DictEnum {

    ATTR_ERR("'dict' object has no attribute 'attr'"),
    ATTR("attr"),
    TYPE_ERR_LIST("unhashable type: 'list'");

    private final String dictEnum;

    /**
     * Initializes dictEnum with string
     * @param dictEnum string being set to dictEnum
     */
    DictEnum(final String dictEnum) {
        this.dictEnum = dictEnum;
    }

    /**
     * Get dictEnum as a string
     * @return dictEnum as a string
     */
    @Override
    public String toString() {
        return dictEnum;
    }
}
