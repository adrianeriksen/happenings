import { combineReducers } from '@reduxjs/toolkit';

import auth from './auth';
import events from './events';

export default combineReducers({
  auth,
  events
});
