package esoe;

import esoe.neway.Identifier;

/**
 * Shard. является основным классом, осуществляющим развертывание, обновление и запуск
 * клиентской части программы.
 * Основные блоки программы:
 * 1. (ui) Слой пользователя. Графический пользовательский интерфейс.
 * 2. (secure) Слой обеспечения безопасности данных. Авторизация, ограничение доступа пользователей.
 * 3. (base) Слой обеспечения доступа к данным. Интерфейс работы с базой данных.
 * 4. (model)Слой обеспечения интерпретации данных (база-модель-пользоваатель). Модель данных.
 * 5. (shards-main)Целевой слой. Модель системы, для реализации которой программа содается.
 * .........................................................................................
 *
 * 6. (log)Логирование - действий пользователей, методов выполняемых программой.
 * 7. (mind)Аналитика работы программы, поведения пользователей.
 * 8. (news)Новостная лента по развитию программы, развитию каждого проекта.
 *
 */
public class Card
{
    private int id; //идентификационный номер компонента
    private String name; //наименование компонента.
    private Card parent; //компонент - от которого появилось ядро, родитель

    public Card(){}
    public Card(String name, Card parent){
        setName(name);
        setID(Identifier.getNext());
        setParent(parent);
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setID(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setParent(Card parent){
        this.parent = parent;
    }
    public Card getParent(){
        Card c = new Card();
        if (parent == null){
            c.setID(0);
            c.setName(getName() + " является корневым ядром");
            c.setParent(null);
            this.parent = c;
        }
        return this.parent;
    }
}
