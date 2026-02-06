module moduletwo {
//    requires moduleone;
    requires transitive moduleone;

    exports com.moduletwo.dtos;
    exports com.moduletwo.service;
}