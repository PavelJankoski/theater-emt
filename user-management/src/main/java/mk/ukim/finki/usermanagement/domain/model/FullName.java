package mk.ukim.finki.usermanagement.domain.model;

import com.sun.istack.NotNull;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;

import java.util.Objects;

public class FullName implements ValueObject {

    private  final String firstName;
    private final String lastName;

    public FullName(@NotNull String firstName, @NotNull String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullName fullName = (FullName) o;
        return Objects.equals(firstName, fullName.firstName) &&
                Objects.equals(lastName, fullName.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "FullName{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }


}
