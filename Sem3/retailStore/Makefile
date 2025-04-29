# Makefile

SRC_DIR=src
OUT_DIR=out
MAIN_CLASS=se.kth.iv1350.retailStore.startup.Main

PKG_DIR=se

SOURCES := $(shell find $(SRC_DIR) -name "*.java")

.PHONY: all run clean doc

all:
	mkdir -p $(OUT_DIR)
	javac -d $(OUT_DIR) $(SOURCES)

run: all
	java -cp $(OUT_DIR) $(MAIN_CLASS)

clean:
	rm -rf $(OUT_DIR)

doc:
	javadoc -subpackages $(PKG_DIR) -sourcepath $(SRC_DIR) -d doc
