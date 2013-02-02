#include <iostream>
#include <string>
#include <Windows.h>

#include <locsim.h>


//
// types
//

class Plugin
{
private: // lifetime
  Plugin()
    : m_pDll(0)
    , m_pApiGetName(0)
    , m_pApiGetVersion(0)
    , m_pApiGetDescription(0)
    , m_pApiInitialize(0)
    , m_pApiRelease(0)
  {}

public:
  ~Plugin()
  {
    if(m_pDll)
    {
      FreeLibrary(m_pDll);
    }
  }

public: // dll stuff
  static Plugin* loadFromFile(std::string rFile)
  {
    HMODULE pDll = LoadLibraryA(rFile.c_str());
    if(!pDll)
    {
      return NULL;
    }

    Plugin* pPlugin = new Plugin();
    pPlugin->m_pDll = pDll;

    pPlugin->m_pApiGetName = (tApiGetName)GetProcAddress(pDll, "getName");
    pPlugin->m_pApiGetVersion = (tApiGetVersion)GetProcAddress(pDll, "getVersion");
    pPlugin->m_pApiGetDescription = (tApiGetDescription)GetProcAddress(pDll, "getDescription");
    pPlugin->m_pApiInitialize = (tApiInitialize)GetProcAddress(pDll, "initialize");
    pPlugin->m_pApiRelease = (tApiRelease)GetProcAddress(pDll, "release");

    if(!pPlugin->isValid())
    {
      std::cerr << "unable to find all methods in dll" << std::endl;
      return NULL;
    }

    return pPlugin;
  }

public: // validity
  bool isValid() const
  {
    return m_pApiGetName
      && m_pApiGetVersion
      && m_pApiGetDescription
      && m_pApiInitialize
      && m_pApiRelease;
  }

public: // interface
  std::string getName() const
  {
    if(!isValid()) throw "invalid plugin";
    return std::string(m_pApiGetName());
  }

  std::string getVersion() const
  {
    if(!isValid()) throw "invalid plugin";
    return std::string(m_pApiGetVersion());
  }

  std::string getDescription() const
  {
    if(!isValid()) throw "invalid plugin";
    return std::string(m_pApiGetDescription());
  }

  bool initialize() const
  {
    if(!isValid()) throw "invalid plugin";
    return m_pApiInitialize();
  }

  void release() const
  {
    if(!isValid()) throw "invalid plugin";
    m_pApiRelease();
  }

private: // members
  HMODULE m_pDll;
  tApiGetName m_pApiGetName;
  tApiGetVersion m_pApiGetVersion;
  tApiGetDescription m_pApiGetDescription;
  tApiInitialize m_pApiInitialize;
  tApiRelease m_pApiRelease;
};


//
// main stuff
//

int main(int argc, char* argv[])
{
  std::string pluginDllFileName = "dispatcher.dll";
  if(argc >= 2)
  {
    pluginDllFileName = std::string(argv[1]);
  }

  std::cout << "loading " << pluginDllFileName << std::endl;

  Plugin* pPlugin = Plugin::loadFromFile(pluginDllFileName);
  if(!pPlugin)
  {
    std::cerr << "error loading plugin " << pluginDllFileName << std::endl;
    return 1;
  }

  if(!pPlugin->initialize())
  {
    std::cerr << "unable to initialize the plugin" << std::endl;
    return 1;
  }

  std::cout << "successfully loaded plugin: " << std::endl;
  std::cout << " name: " << pPlugin->getName() << " " << pPlugin->getVersion() << std::endl;
  std::cout << " desc: " << pPlugin->getDescription() << std::endl;

  pPlugin->release();
  delete pPlugin;
  pPlugin = 0;

  return 0;
}

