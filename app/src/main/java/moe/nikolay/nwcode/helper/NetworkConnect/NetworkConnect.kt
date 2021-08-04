package moe.nikolay.nwcode.helper.NetworkConnect

interface NetworkConnect {

    /**
     * Проверка есть ли соединение с интернетом вообще
     * **/
    fun isConnected(): Boolean

}