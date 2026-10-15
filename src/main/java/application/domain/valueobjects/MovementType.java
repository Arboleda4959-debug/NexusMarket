package application.domain.valueobjects;

/** Inventory movement categories; a persistence adapter should record each movement. */
public enum MovementType { INFLOW, RESERVATION, SALE, ADJUSTMENT, RETURN }
