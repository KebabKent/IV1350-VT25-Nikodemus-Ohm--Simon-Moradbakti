# Makefile

SRC_DIR=src
OUT_DIR=out
MAIN_CLASS=se.kth.iv1350.retailStore.startup.Main

PKG_DIR=se

SOURCES := $(shell find $(SRC_DIR) -name "*.java")

.PHONY: all run clean doc viewDoc compTest test

all:
	mkdir -p $(OUT_DIR)
	javac -d $(OUT_DIR) $(SOURCES)

run: all
	java -cp $(OUT_DIR) $(MAIN_CLASS)

clean:
	rm -rf $(OUT_DIR)

doc:
	javadoc -subpackages $(PKG_DIR) -sourcepath $(SRC_DIR) -d doc

viewDoc:
	xdg-open doc/index.html

# compTest:
# 	javac -cp "lib/*:out" -d out_test $(find test -name "*.class")

# test:
# 	java -cp "lib/*:out:out_test" org.junit.platform.console.ConsoleLauncher \
#     --scan-class-path