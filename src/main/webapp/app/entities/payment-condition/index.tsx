import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import PaymentCondition from './payment-condition';
import PaymentConditionDetail from './payment-condition-detail';
import PaymentConditionUpdate from './payment-condition-update';
import PaymentConditionDeleteDialog from './payment-condition-delete-dialog';

const PaymentConditionRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<PaymentCondition />} />
    <Route path="new" element={<PaymentConditionUpdate />} />
    <Route path=":id">
      <Route index element={<PaymentConditionDetail />} />
      <Route path="edit" element={<PaymentConditionUpdate />} />
      <Route path="delete" element={<PaymentConditionDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default PaymentConditionRoutes;
