module modulethree {
    requires moduletwo;
    requires moduleone;
}

// 3 -> 2
// 2 -> 1  uses transitive requirement to moduleone