package br.com.markus.enuns;

/**
 * Enum que contém os tipos de pagamentos
 */
public enum LogTypeEnum {
    CSTM_PRDT_VIEW("CUSTUMER_PRODUCT_VIEW"),
    CSTM_SRCH_DONE("CUSTUME_SEARCH_DONE"),
    APPL_INFO_LOG("APPL_INFO_LOG"),
    APPL_WARN_LOG("APPL_WARN_LOG"),
    APPL_DEBUG_LOG("APPL_DEBUG_LOG"),
    APPL_ERROR_LOG("APPL_ERROR_LOG"),
    APPL_FATAL_LOG("APPL_FATAL_LOG");

    private String description;

    LogTypeEnum(String description) {
        this.description = description;
    }

    public static LogTypeEnum from(final String value) {
        if (value == null) {
            return null;
        }

        for (LogTypeEnum e : LogTypeEnum.values()) {
            if (value.equals(e.getDescription())) {
                return e;
            }
        }
        return null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
