## Release

Regenerate the GPG key if needed:
```shell
# Provide name, email and GPG signing password
gpg --gen-key

# Obtain GPG secret signing key in base64 format
export GPG_SIGNING_KEY=$(gpg -a --export-secret-keys hsiliev@gmail.com | base64 -w0)
export GPG_SIGNING_PASSWORD=<password>
```

Get the new public key with: `gpg --export -a "Hristo Iliev"` and publish it on http://keyserver.ubuntu.com

Open the staging repo on https://s01.oss.sonatype.org/#stagingRepositories and activate the checks by closing the corresponding repo to activate the checks.

If all checks are ok, release the artifact to Maven Central.
