# **2017_Compiler_Item3**

## [1. Basic Block]
>__1_While_stmt__ <br>
>__2_If_stmt__ <br>
>__3_For_stmt__ <br>
>__4_Switch_stmt__ <br>

## [2. Addtional Grammer]
>__1_For_stmt__ <br>
>__2_Switch_stmt__ <br>

## [3. Addtional Type]
>__1_Float__ <br>
>__2_Double__ <br>

## [3. Type_Checking]
>__1_Var_decl__ <br>
>__2_Local_decl__ <br>
>__3_Param__ <br>
>__4_Expr__ <br>
<pre>1. Store variable's type in static HashMap(named "GlobalVariableMap, LocalVariableMap") at Var_decl, Local_decl
2. Do Type check at Var_decl, Local_decl, Param, Expr using type stored in prev step's map</pre>
