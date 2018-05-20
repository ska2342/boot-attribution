# boot-attribution

A boot-clj plug-in to create correct attribution

# Project Status

This is just some early code. I mean to turn this into a working
library. Only time will tell.

PRs welcome

# Project Goals

Building an application, free or commercially, with boot should be
easy. For this, you have to give attribution to authors of libraries
you depend on.

In addition to just listing the licenses, copyright notices should
also be given if required.

We'll apply some heuristics, similar to how
[lein-licenses](https://github.com/technomancy/lein-licenses) does it
and also allow manual override for each dependency. 

License names should be normalized.

Maybe support for SPDX will be added. Given how unaccessible SPDX'
information is, there's reason to doubt that it will gain traction. 

If [gradle-clojure](https://github.com/gradle-clojure/gradle-clojure)
reaches a state where I can use it for my own project, I may turn
there and just use Gradle's plug-ins which probably solved tons of
nitty gritty details already.

# License

EPL-1.0
