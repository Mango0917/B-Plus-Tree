JC = javac
JFLAGS = -g
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	KeyValPair.java \
	BPlusNode.java \
	BPlusSearch.java \
	BPlusRSearch.java \
	BPlusTree.java \
	treesearch.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class