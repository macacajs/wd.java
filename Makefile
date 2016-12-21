git_version = $$(git branch 2>/dev/null | sed -e '/^[^*]/d'-e's/* \(.*\)/\1/')

all: test

doc:
	javadoc -d ./docs sourcepath ./src/main/java/macaca/client/MacacaClient.java

.PHONY: test
