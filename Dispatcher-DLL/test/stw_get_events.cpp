
#define ERROR_OK 0
#define ERORR_...

#define EVENT_TYPE_SIGNAL 1
#define EVENT_TYPE_BALISE 2
#define EVENT_TYPE_WEICHE 3

int stw_get_events(int* count, int** types, int** ids)
{
  *count = 2;
  *types = new int[count];
  *ids = new int[count];

  types[0] = EVENT_TYPE_SIGNAL;
  ids[0] = 23;

  types[1] = EVENT_TYPE_WEICHE;
  ids[1] = 7;

  return 0;
}


int main(int argc, char** argv)
{
  int count = 0;
  int* types = NULL;
  int* ids = NULL;

  if(0 != stw_get_events(&count, &types, &ids))
  {
    std::cerr << "error!" << std::endl;
    return 1;
  }

  for(int i = 0; i < count; i++)
  {
    int event_type = types[i];
    int event_id = ids[i];

    std::cout << "got event for type: " << event_type << " with id " << event_id << std::endl;

    switch(event_type)
    {
      case EVENT_TYPE_SIGNAL:
        stw_get_signal(event_id, ...);
        break;
      case EVENT_TYPE_BALISE:
        stw_get_balise(event_id, ...);
        break;
      case EVENT_TYPE_WEICHE:
        stw_get_weiche(event_id, ...);
        break;
    }
  }

  return 0;
}
