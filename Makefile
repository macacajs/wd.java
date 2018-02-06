all: doc

doc:
	javadoc -d ./docs -version -sourcepath ./src/main/java macaca.client macaca.client.commands macaca.client.common macaca.client.model
deploy:
	mvn -s settings.xml deploy -Dmaven.test.skip=true
.PHONY: doc
