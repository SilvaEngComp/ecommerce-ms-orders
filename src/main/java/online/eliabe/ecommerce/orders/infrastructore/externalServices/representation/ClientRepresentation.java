package online.eliabe.ecommerce.orders.infrastructore.externalServices.representation;

import jakarta.persistence.Column;

public record ClientRepresentation( Long code,
                                    String name,
                                    String cpf,
                                    String street,
                                    String houseNumber,
                                    String district,
                                    String email,
                                    String phone) {
}
