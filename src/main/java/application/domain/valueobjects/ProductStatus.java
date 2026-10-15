package application.domain.valueobjects;

/** PUBLISHED/SUSPENDED/DISCONTINUED are the canonical catalog states. */
public enum ProductStatus {
    PUBLISHED,
    SUSPENDED,
    DISCONTINUED,
    /** @deprecated use PUBLISHED for newly created products. */
    @Deprecated ACTIVE,
    /** @deprecated use SUSPENDED for newly created products. */
    @Deprecated INACTIVE,
    /** Inventory condition; stock availability should primarily come from Inventory. */
    @Deprecated OUT_OF_STOCK
}
