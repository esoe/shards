package esoe;

import java.util.ArrayList;

public class Core {
    private Shard shard = new Shard();//содержит id, name ядра, а также ссылку на родительское ядро
    private ArrayList<ModelCore> sister;//список сестричек, атрибутов родительского ядра
    private ArrayList<ModelCore> attribute = new ArrayList();//список атрибутов

    //создание проекта
    public Core(){ setDefaultCore(); }
    private void setDefaultCore(){
        this.shard.setID(Identifier.getNext());
        //название проекта должно браться с сервера из перечня проектов
        this.shard.setName("unnamed project");
        //с нулевым родителем походу придется постоянно проверять в будущем, не нулевой ли он.
        this.shard.setParent(null);
        //атрибуты, создаваемые по умолчанию
        this.attribute.add(new ModelCore("discription", this.shard.getParent()));
        //создаем модель "unnamed project", добавляем запись о ядре
        //список core является списком атрибутов родителя, в него добавляются сестрички текущего core
        sister = new ArrayList<>();
        this.sister.add(new ModelCore(this.shard.getName(), this.shard.getParent()));
    }


}
