%-----------------------------------------------------------------------------%
% Require adjacent rows and adjacent columns in the array 'x' to be
% lexicographically ordered.  Adjacent rows and adjacent columns cannot be
% equal.
%-----------------------------------------------------------------------------%

include "lex_less.mzn";

predicate strict_lex2(array[int, int] of var int: x) =
    lex2Choco(x,true);
