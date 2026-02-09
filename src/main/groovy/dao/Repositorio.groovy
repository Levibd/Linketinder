package dao

interface Repositorio<T> {

    List<T> listar()
    void salvar(T t)

}