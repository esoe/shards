package esoe;

import java.util.ArrayList;

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
public class Shard
{
    private String name; //наименование компонента.
    // Компонент может быть сформирован только когда установлено его название.
    private int id; //идентификационный номер компонента
    //private String discription;//это новый объект Shard - атрибут, который вместо названия содержит описание
    private Shard parent; //компонент - от которого появилось ядро, родитель
    //private ArrayList<Shard> attribute;//список атрибутов ядра
    /**
     * идентификация вершин графа
     */

    public Shard(){}
    public Shard(String name, Shard parent){
        setName(name);
        setID(Identifier.getNext());
        setParent(parent);
    }
    public void setName(String name){ this.name = name; }
    public String getName(){ return this.name; }
    public void setID(int id){ this.id = id; }
    public int getId(){ return this.id; }
    public void setParent(Shard parent){ this.parent = parent; }
    public Shard getParent(){ return this.parent; }

    public static void main( String[] args )
    {
        System.out.println( "запущен метод main класса Shard проекта shards  ... " );
    }
}
