with import <nixpkgs> {};
mkShell {
  nativeBuildInputs = [
    bashInteractive
  ];
  buildInputs = [
    tomcat85
    spring-boot
    maven
    jdk8
  ];
}
