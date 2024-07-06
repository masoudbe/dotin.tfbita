import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import TradeTypeCode from './trade-type-code';
import TradeTypeCodeDetail from './trade-type-code-detail';
import TradeTypeCodeUpdate from './trade-type-code-update';
import TradeTypeCodeDeleteDialog from './trade-type-code-delete-dialog';

const TradeTypeCodeRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<TradeTypeCode />} />
    <Route path="new" element={<TradeTypeCodeUpdate />} />
    <Route path=":id">
      <Route index element={<TradeTypeCodeDetail />} />
      <Route path="edit" element={<TradeTypeCodeUpdate />} />
      <Route path="delete" element={<TradeTypeCodeDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default TradeTypeCodeRoutes;
