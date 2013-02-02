#include <iostream>
#include <string>

extern "C" {

  __declspec(dllexport) const char* getName()
  {
    return "DispatcherPlugin";
  }

  __declspec(dllexport) const char* getVersion()
  {
    return "0.1";
  }

  __declspec(dllexport) const char* getDescription()
  {
    return "Some awesome description!!111elf";
  }

  __declspec(dllexport) bool initialize()
  {
    std::cout << "initializing!!!!!!" << std::endl;
    return true;
  }

  __declspec(dllexport) void release()
  {
    std::cout << "releasing!!!!!!" << std::endl;
  }

};

int main()
{
	std::cout << "hello World!" << std::endl;
	system("PAUSE");

	return 0;
}