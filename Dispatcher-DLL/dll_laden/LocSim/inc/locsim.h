#pragma once

extern "C"
{
  typedef const char* (*tApiGetName)(void);
  typedef const char* (*tApiStw_getVersion)(void);
  typedef const char* (*tApiGetDescription)(void);
  typedef int        (*tApiInitialize)(void);
  typedef int        (*tApiRelease)(void);
};
