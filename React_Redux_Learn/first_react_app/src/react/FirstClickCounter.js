import React from 'react';
import { stringFormat } from '../Utilities';

class FirstClickCounter extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    var content = stringFormat('The first counter: {0}', this.props.currentValue);
    return (
      <div>
        <div>{content}</div>
      </div>
    );
  }
}

export default FirstClickCounter;