package Items.Interfaces;

import Items.Character.BaseCharacter;

public interface Attackable {
    // this interface implement on character that can attack others
    void attack(Object o);
    int getAttackRange();
}
