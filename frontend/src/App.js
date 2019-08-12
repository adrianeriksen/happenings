import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import { makeStyles } from '@material-ui/styles';
import { Container, Typography } from '@material-ui/core';

import Events from './Events';
import Login from './Login';

const useStyles = makeStyles({
  container: {
    marginTop: 64
  }
});

function App() {
  const classes = useStyles();

  return (
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
  );
}

export default App;
