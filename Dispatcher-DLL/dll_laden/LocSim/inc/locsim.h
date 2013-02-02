#pragma once

extern "C"
{
  typedef const char* (*tApiGetName)(void);
  typedef const char* (*tApiGetVersion)(void);
  typedef const char* (*tApiGetDescription)(void);
  typedef bool        (*tApiInitialize)(void);
  typedef void        (*tApiRelease)(void);
};
