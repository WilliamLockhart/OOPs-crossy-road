.PHONY: run compile clean

JAVA=java
JAVAC=javac
JFX_LIB=lib
SRC=src
OUT=out

JFX_MODULES=javafx.controls,javafx.graphics

run: compile
	$(JAVA) --module-path $(JFX_LIB) --add-modules $(JFX_MODULES) -cp $(OUT) Main

compile:
	mkdir -p $(OUT)
	$(JAVAC) --module-path $(JFX_LIB) --add-modules $(JFX_MODULES) -d $(OUT) $(shell find $(SRC) -name "*.java")

clean:
	rm -rf $(OUT)
