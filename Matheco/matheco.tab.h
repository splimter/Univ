/* A Bison parser, made by GNU Bison 2.1.  */

/* Skeleton parser for Yacc-like parsing with Bison,
   Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005 Free Software Foundation, Inc.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA 02110-1301, USA.  */

/* As a special exception, when this file is copied by Bison into a
   Bison output file, you may use that output file without restriction.
   This special exception was added by the Free Software Foundation
   in version 1.24 of Bison.  */

/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     NUM = 258,
     END = 259,
     VAR = 260,
     LP = 261,
     RP = 262,
     AFFECT = 263,
     VARNAME = 264,
     KWMAX = 265,
     KWMIN = 266,
     KWSORT = 267,
     KWADD = 268,
     KWSUB = 269,
     KWMUL = 270,
     KWDIV = 271,
     KWFACT = 272,
     KWSQRT = 273,
     KWPOW = 274,
     KWLOG = 275,
     KWEXP = 276,
     KWCOS = 277,
     KWSIN = 278,
     KWTAN = 279,
     KWCOSR = 280,
     KWSINR = 281,
     KWTANR = 282,
     KWEQ = 283,
     KWNEQ = 284,
     KWGEQ = 285,
     KWLEQ = 286
   };
#endif
/* Tokens.  */
#define NUM 258
#define END 259
#define VAR 260
#define LP 261
#define RP 262
#define AFFECT 263
#define VARNAME 264
#define KWMAX 265
#define KWMIN 266
#define KWSORT 267
#define KWADD 268
#define KWSUB 269
#define KWMUL 270
#define KWDIV 271
#define KWFACT 272
#define KWSQRT 273
#define KWPOW 274
#define KWLOG 275
#define KWEXP 276
#define KWCOS 277
#define KWSIN 278
#define KWTAN 279
#define KWCOSR 280
#define KWSINR 281
#define KWTANR 282
#define KWEQ 283
#define KWNEQ 284
#define KWGEQ 285
#define KWLEQ 286




#if ! defined (YYSTYPE) && ! defined (YYSTYPE_IS_DECLARED)
#line 92 "matheco.y"
typedef union YYSTYPE {
    int integer;
    char *str; 
} YYSTYPE;
/* Line 1447 of yacc.c.  */
#line 105 "matheco.tab.h"
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif

extern YYSTYPE yylval;



