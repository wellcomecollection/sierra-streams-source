
sonatypeProfileName := "uk.ac.wellcome"

publishMavenStyle := true

licenses := Seq("MIT" -> url("https://github.com/wellcometrust/sierra-streams-source/blob/master/LICENSE"))
homepage := Some(url("https://github.com/wellcometrust/sierra-streams-source"))
scmInfo := Some(
  ScmInfo(
    url("https://github.com/wellcometrust/sierra-streams-source"),
    "scm:git@github.com:wellcometrust/sierra-streams-source.git"
  )
)
developers := List(
  Developer(id="alicefuzier", name="Alice Fuzier Cayla", email="a.fuzier-cayla@wellcome.ac.uk", url=url("https://github.com/alicefuzier")),
  Developer(id="robert_kenny", name="Robert Kenny", email="r.kenny@wellcome.ac.uk", url=url("https://github.com/kenoir"))
)