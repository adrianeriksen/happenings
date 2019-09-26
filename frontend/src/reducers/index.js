import { combineReducers } from 'redux-starter-kit';

import auth from './auth';
import events from './events';

export default combineReducers({
  auth,
  events
});
