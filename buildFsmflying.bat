@d:
@cd D:\dev\Git\ReposBase\Fsmflying\Fsmflying\bin
@mkdir resource
@echo dbtype=sqlite > resource/exampledb.properties
@echo driver=jdbc:sqlite:D:/Dev/sqlite/sqlite-tools-win32-x86-3150200/test3.db >> resource/exampledb.properties
@echo username= >> resource/exampledb.properties
@echo password= >> resource/exampledb.properties
@jar cvf D:\dev\Git\ReposBase\fsmflying-1.0.jar com ../../resource