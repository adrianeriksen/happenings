import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import { makeStyles } from '@material-ui/styles';
import { Container, Typography } from '@material-ui/core';

import Events from './Events';
import Login from './Login';
import UserContext from './UserContext';

const useStyles = makeStyles({
  container: {
    marginTop: 64
  }
});

function App() {
  const classes = useStyles();

  const [currentUser, setCurrentUser] = useState(null);

  return (
    <UserContext.Provider value={{ currentUser, setCurrentUser }}>
      <Router>
        <Container maxWidth="sm" className={classes.container}>
          <Typography variant="h2" component="h1">
            Happenings
          </Typography>
          <Switch>
            <Route path="/" exact component={Events} />
            <Route path="/login" component={Login} />
          </Switch>
        </Container>
      </Router>
    </UserContext.Provider>
  );
}

export default App;
