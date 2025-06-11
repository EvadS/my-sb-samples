package ua.se.sample.easynotes.entity.converter;




import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ua.se.sample.easynotes.dto.enums.IpsRole;


@Converter(autoApply = true)
    public class IpsRoleConverter implements AttributeConverter<IpsRole, Integer> {

        @Override
        public Integer convertToDatabaseColumn(IpsRole category) {
            if (category == null) {
                return null;
            }
            return category.getCode();
        }

        @Override
        public IpsRole convertToEntityAttribute(Integer code) {
            if (code == null) {
                return null;
            }

            return IpsRole.of(code);
        }
    }
