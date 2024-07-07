import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './custom-justification-child.reducer';

export const CustomJustificationChild = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const customJustificationChildList = useAppSelector(state => state.customJustificationChild.entities);
  const loading = useAppSelector(state => state.customJustificationChild.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="custom-justification-child-heading" data-cy="CustomJustificationChildHeading">
        <Translate contentKey="tfbitaApp.customJustificationChild.home.title">Custom Justification Children</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.customJustificationChild.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/custom-justification-child/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.customJustificationChild.home.createLabel">Create new Custom Justification Child</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {customJustificationChildList && customJustificationChildList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.customJustificationChild.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('item')}>
                  <Translate contentKey="tfbitaApp.customJustificationChild.item">Item</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('item')} />
                </th>
                <th className="hand" onClick={sort('tariffCode')}>
                  <Translate contentKey="tfbitaApp.customJustificationChild.tariffCode">Tariff Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tariffCode')} />
                </th>
                <th className="hand" onClick={sort('productName')}>
                  <Translate contentKey="tfbitaApp.customJustificationChild.productName">Product Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('productName')} />
                </th>
                <th className="hand" onClick={sort('productId')}>
                  <Translate contentKey="tfbitaApp.customJustificationChild.productId">Product Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('productId')} />
                </th>
                <th className="hand" onClick={sort('boxCount')}>
                  <Translate contentKey="tfbitaApp.customJustificationChild.boxCount">Box Count</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('boxCount')} />
                </th>
                <th className="hand" onClick={sort('boxType')}>
                  <Translate contentKey="tfbitaApp.customJustificationChild.boxType">Box Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('boxType')} />
                </th>
                <th className="hand" onClick={sort('pureWeight')}>
                  <Translate contentKey="tfbitaApp.customJustificationChild.pureWeight">Pure Weight</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pureWeight')} />
                </th>
                <th className="hand" onClick={sort('impureWeight')}>
                  <Translate contentKey="tfbitaApp.customJustificationChild.impureWeight">Impure Weight</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('impureWeight')} />
                </th>
                <th className="hand" onClick={sort('amountCurrency')}>
                  <Translate contentKey="tfbitaApp.customJustificationChild.amountCurrency">Amount Currency</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amountCurrency')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.customJustificationChild.customJustification">Custom Justification</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {customJustificationChildList.map((customJustificationChild, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/custom-justification-child/${customJustificationChild.id}`} color="link" size="sm">
                      {customJustificationChild.id}
                    </Button>
                  </td>
                  <td>{customJustificationChild.item}</td>
                  <td>{customJustificationChild.tariffCode}</td>
                  <td>{customJustificationChild.productName}</td>
                  <td>{customJustificationChild.productId}</td>
                  <td>{customJustificationChild.boxCount}</td>
                  <td>{customJustificationChild.boxType}</td>
                  <td>{customJustificationChild.pureWeight}</td>
                  <td>{customJustificationChild.impureWeight}</td>
                  <td>{customJustificationChild.amountCurrency}</td>
                  <td>
                    {customJustificationChild.customJustification ? (
                      <Link to={`/custom-justification/${customJustificationChild.customJustification.id}`}>
                        {customJustificationChild.customJustification.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/custom-justification-child/${customJustificationChild.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/custom-justification-child/${customJustificationChild.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/custom-justification-child/${customJustificationChild.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="tfbitaApp.customJustificationChild.home.notFound">No Custom Justification Children found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default CustomJustificationChild;
