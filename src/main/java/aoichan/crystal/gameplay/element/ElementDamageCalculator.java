package aoichan.crystal.gameplay.element;

// [!] Code: Element damage logic
public class ElementDamageCalculator {

    public static double applyElementBonus(
            double damage,
            ElementType element
    ) {

        return switch (element) {

            case FIRE -> damage * 1.15;

            case FROST -> damage * 1.10;

            case THUNDER -> damage * 1.20;

            case HOLY -> damage * 1.12;

            case VOID -> damage * 1.25;

            default -> damage;
        };
    }
} 
