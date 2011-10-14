SRCS= $(wildcard *.java)
OBJS= $(SRCS:.java=.class) 

JC= javac
JCFLAGS=

all: $(OBJS)

re: clean
	touch $(SRCS)
	$(MAKE) all

doc: $(SRCS)
	javadoc -d doc $(SRCS)

clean:
	rm -f *.class *~

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JCFLAGS) $<
