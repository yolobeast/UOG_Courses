All: libpm.a yadc JNIwrapper javamake createfile
trytests: libpm.a tests
clean:
	@rm *.o bin/*.class *.class *.log lib/libJNIpm.so libpm.a example1 bin/yadc bin/create a1test

libpm.a: ParameterManager.o ParameterList.o hash.o
	ar cr lib/libpm.a ParameterManager.o ParameterList.o hash.o
	ranlib lib/libpm.a

createfile:
	gcc creatfile.c -o bin/create

yadc: yacc.o lex.o ParameterList.o hash.o
	gcc lex.o yacc.o ParameterList.o hash.o -o bin/yadc -ll -ly

lex.o: yadc.l
	lex yadc.l
	gcc lex.yy.c -c -o lex.o

yacc.o: yadc.y
	yacc yadc.y -d -y
	gcc y.tab.c -c -o yacc.o

javamake: Dialog.java
	javac Dialog.java -d bin

JNIwrapper: JNIwrapper.java JNIwrapper.h libpm.a
	gcc JNIwrapper.c -c -fPIC -I/usr/lib/jvm/java-1.6.0-openjdk/include -I/usr/lib/jvm/java-1.6.0-openjdk/include/linux
	gcc -shared -Wl,-soname,libJNIpm.so -I/path/to/java/include -I/path/to/java/include/linux -o lib/libJNIpm.so JNIwrapper.o -L./lib -lpm
	javac JNIwrapper.java -d bin

ParameterManager.o: ParameterManager.c ParameterManager.h ParameterList.h hash.c
	gcc -fPIC ParameterManager.c -c
ParameterList.o: ParameterList.c ParameterList.h
	gcc -fPIC ParameterList.c -c
hash.o: hash.c hash.h
	gcc -fPIC hash.c -c
