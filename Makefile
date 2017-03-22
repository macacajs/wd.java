git_version = $$(git branch 2>/dev/null | sed -e '/^[^*]/d'-e's/* \(.*\)/\1/')

all: test

doc:
	javadoc -d ./docs -version -sourcepath ./src/main/java macaca.client macaca.client.commands macaca.client.common macaca.client.model
deploy:
	mvn -s settings.xml deploy
.PHONY: test
