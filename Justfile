set shell := ["bash", "-euo", "pipefail", "-c"]

maven := "./mvnw --batch-mode --no-transfer-progress -pl library-core"

unit-test:
    {{ maven }} clean test

integration-test:
    {{ maven }} clean test-compile failsafe:integration-test failsafe:verify

package:
    {{ maven }} clean package -Dmaven.test.skip=true

build: unit-test integration-test package
