set shell := ["bash", "-euo", "pipefail", "-c"]

maven := "./mvnw --batch-mode --no-transfer-progress -pl library-core"
test_names := "'-Djunit.jupiter.displayname.generator.default=org.junit.jupiter.api.DisplayNameGenerator$Simple'"

unit-test:
    {{ maven }} {{ test_names }} clean test

integration-test:
    {{ maven }} {{ test_names }} clean test-compile failsafe:integration-test failsafe:verify

package:
    {{ maven }} clean package -Dmaven.test.skip=true

build: unit-test integration-test package
