package cn.pbj.demo2020.book.effective_java3.chapter2.item1;

public class Provider implements IProvider{

    private IAnimal iAnimal;

    public Provider(IAnimal iAnimal){
        this.iAnimal = iAnimal;
    }

    @Override
    public IAnimal ianimal() {
        return iAnimal;
    }

}
