# **2017_Compiler_Item3**

## [1. Basic Block]
>__While_stmt__ <br>
>__If_stmt__ <br>
>__For_stmt__ <br>
>__Switch_stmt__ <br>

## [2. Addtional Grammer]
>__For_stmt__ <br>
>__Switch_stmt__ <br>

## [3. Addtional Type]
>__Float__ <br>
>__Double__ <br>

## [3. Type_Checking]
>__Var_decl__ <br>
>__Local_decl__ <br>
>__Param__ <br>
>__Expr__ <br>
<pre> 1. Store variable's type in static HashMap(named "GlobalVariableMap, LocalVariableMap") at Var_decl, Local_decl
2. Do Type check at Var_decl, Local_decl, Param, Expr using type stored in prev step's map</pre>
